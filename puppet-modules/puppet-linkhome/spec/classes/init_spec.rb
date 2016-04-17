require 'spec_helper'
describe 'linkhome' do

  context 'with defaults for all parameters' do
    it { should contain_class('linkhome') }
  end
end
