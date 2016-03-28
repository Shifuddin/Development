require 'spec_helper'
describe 'lpkgrepo' do

  context 'with defaults for all parameters' do
    it { should contain_class('lpkgrepo') }
  end
end
