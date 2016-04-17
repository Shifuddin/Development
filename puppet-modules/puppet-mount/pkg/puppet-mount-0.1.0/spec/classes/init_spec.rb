require 'spec_helper'
describe 'mount' do

  context 'with defaults for all parameters' do
    it { should contain_class('mount') }
  end
end
